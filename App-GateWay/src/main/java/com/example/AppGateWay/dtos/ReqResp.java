package com.example.AppGateWay.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ReqResp<T>  {

    private T data;
    private String msg;
    
    public String getMsg() {
        return this.msg;
    }
    public T getData() {
        return this.data;
    }
}