package com.example.Staff.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Staff.dtos.StaffDto;
import com.example.Staff.entity.Staff;
import com.example.Staff.repository.StaffRepository;

@Service
public class StaffServiceImpl implements StaffService{

	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private KafkaService kafkaService;
	
	
	@Override
	public void deleteById(Long id) {
		String kafkaMessage="";
		Staff staff =staffRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("staff with given id "+id+" doesn't exist"));
		kafkaMessage="Staff with id "+id+" had been deleted";
		kafkaService.sendMessageToKafka(kafkaMessage);
		staffRepository.delete(staff);
	}

	@Override
	public StaffDto getStaffById(Long id) {
		Staff staff =staffRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("staff with given id "+id+" doesn't exist"));
		return convertToDto(staff);
	}
	
	private StaffDto convertToDto(Staff staff) {
		StaffDto staffDto=new StaffDto();
		BeanUtils.copyProperties(staff, staffDto);
		return staffDto;
	}

	@Override
	public List<StaffDto> getAllStaff() {
		List<Staff> staffs= staffRepository.findAll();
		return staffs.stream().map((staff)-> convertToDto(staff)).collect(Collectors.toList());
	}

	@Override
	public StaffDto save(StaffDto staffDto) {
		String kafkString="";
		Staff staff=new Staff();
		if(staffDto.getId()==null || staffDto.getId()==0) {
			BeanUtils.copyProperties(staffDto, staff);
			kafkString="new staff has been saved successfully";
			kafkaService.sendMessageToKafka(kafkString);
		}
		else {
			Staff staffDb =staffRepository.findById(staffDto.getId())
					.orElseThrow(()-> new IllegalArgumentException("staff with given id "+staffDto.getId()+" doesn't exist"));
			BeanUtils.copyProperties(staffDto, staff);
			kafkString="staff has been updated successfully";
			kafkaService.sendMessageToKafka(kafkString);
		}
		
		return convertToDto(staffRepository.save(staff));
	}

}
