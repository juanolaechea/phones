package com.utn.phones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallDto {

   private Long duration;
   private Float totalPrice;
   private Float priceXmin;
}
