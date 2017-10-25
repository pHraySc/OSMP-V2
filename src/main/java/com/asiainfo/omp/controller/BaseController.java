package com.asiainfo.omp.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.asiainfo.omp.service.BaseMonitorService;

public class BaseController {
   @Autowired
   protected BaseMonitorService baseMonitorService;
}
