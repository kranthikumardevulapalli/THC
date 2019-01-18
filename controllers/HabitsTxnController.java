package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.model.Habit;
import com.model.HabitsTxn;
import com.model.HabitsTxnDtl;
import com.service.HabitService;
import com.service.IHabitsTxnService;

@CrossOrigin(origins ="*")
@Controller
@RequestMapping("api/txn")
public class HabitsTxnController {
	@Autowired
	private IHabitsTxnService habitTxnService;

	@Autowired
	private HabitService habitService;
	
	@GetMapping("habitTxn/{id}")
	public ResponseEntity<HabitsTxn> getById(@PathVariable("id") String no) {
		HabitsTxn test = habitTxnService.getByMrno(no);
		return new ResponseEntity<HabitsTxn>(test, HttpStatus.OK);
	}
	@RequestMapping(value = "/habitTxn",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Void> createHabitTxn(@RequestBody HabitsTxn habitTxn,UriComponentsBuilder builder) {
		HabitsTxn txn=new HabitsTxn();
		System.out.println("Posting...!");
		txn.setMediaclRecordNo(habitTxn.getMediaclRecordNo());
		txn.setAdditionalNotes(habitTxn.getAdditionalNotes());
		
		HabitsTxnDtl txndtl;
		

		List<HabitsTxnDtl> list = new ArrayList<>();

		for (int i = 0; i < habitTxn.getHabitsTxnDtls().size(); i++) {
			
			txndtl = new HabitsTxnDtl();
			txndtl.setHabitsTxn(txn);
			txndtl.setCreatedBy(habitTxn.getHabitsTxnDtls().get(i).getCreatedBy());
			txndtl.setCreatedOn(habitTxn.getHabitsTxnDtls().get(i).getCreatedOn());
			txndtl.setDescription(habitTxn.getHabitsTxnDtls().get(i).getDescription());
			txndtl.setHabit(habitTxn.getHabitsTxnDtls().get(i).getHabit());
			txndtl.setUpdatedBy(habitTxn.getHabitsTxnDtls().get(i).getUpdatedBy());
			txndtl.setUpdatedOn(habitTxn.getHabitsTxnDtls().get(i).getUpdatedOn());
			list.add(txndtl);
		}
		txn.setHabitsTxnDtls(list);
		habitTxnService.createHabitTxn(txn);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/habitTxnUpdate",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<Void> updateHabitTxn(@RequestBody HabitsTxn habitTxn,UriComponentsBuilder builder) {
		HabitsTxn txn=new HabitsTxn();
		System.out.println("updating...!");
		txn.setId(habitTxn.getId());
		txn.setMediaclRecordNo(habitTxn.getMediaclRecordNo());
		txn.setAdditionalNotes(habitTxn.getAdditionalNotes());
		
		HabitsTxnDtl txndtl;
		

		List<HabitsTxnDtl> list = new ArrayList<>();
			
			txndtl = new HabitsTxnDtl();
			txndtl.setHabitsTxn(txn);
			txndtl.setId(habitTxn.getHabitsTxnDtls().get(0).getId());
			txndtl.setCreatedBy(habitTxn.getHabitsTxnDtls().get(0).getCreatedBy());
			txndtl.setCreatedOn(habitTxn.getHabitsTxnDtls().get(0).getCreatedOn());
			txndtl.setDescription(habitTxn.getHabitsTxnDtls().get(0).getDescription());
			txndtl.setHabit(habitTxn.getHabitsTxnDtls().get(0).getHabit());
			txndtl.setUpdatedBy(habitTxn.getHabitsTxnDtls().get(0).getUpdatedBy());
			txndtl.setUpdatedOn(habitTxn.getHabitsTxnDtls().get(0).getUpdatedOn());
			list.add(txndtl);
		txn.setHabitsTxnDtls(list);
		habitTxnService.updateHabitTxn(txn);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping("habitsList")
	public ResponseEntity<List<Habit>> getAllHabits() {
		List<Habit> habit = habitService.getAllHabits();
		return new ResponseEntity<List<Habit>>(habit, HttpStatus.OK);
	}
	
	@GetMapping("habits/{id}")
	public ResponseEntity<Habit> getByHabitId(@PathVariable("id") int no) {
		Habit habit = habitService.getByHabit(no);
		return new ResponseEntity<Habit>(habit, HttpStatus.OK);
	}
	
}
