package com.comulynx.wallet.rest.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.comulynx.wallet.rest.api.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comulynx.wallet.rest.api.model.Webuser;
import com.comulynx.wallet.rest.api.repository.WebuserRepository;
import com.comulynx.wallet.rest.api.util.AppUtils;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(AppUtils.BASE_URL+"/webusers")
public class WebuserController {

	@Autowired
	private WebuserRepository webuserRepository;

	@GetMapping("/")
	public List<Webuser> getAllWebusers() {
		return webuserRepository.findAll();
	}

	@GetMapping("/{employeeId}")
	public ResponseEntity<Webuser> getWebuserByEmployeeId(@PathVariable(value = "employeeId") String employeeId)
			throws ResourceNotFoundException {
		Webuser webuser = webuserRepository.findByEmployeeId(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Webuser not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(webuser);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createWebuser(@RequestBody Webuser webuser) {
		try {

			if(webuserRepository.findByEmployeeId(webuser.getEmployeeId()).isPresent()) {
				throw new WebUserWithEmployeeIdExistsExcpetion("Web user with employee id: " + webuser.getEmployeeId() + "already exists");
			}

			if (webuserRepository.findByUsername(webuser.getUsername()) != null) {
				throw new WebUserWithUsernameExistsException("Web user with username: " + webuser.getUsername() + "already exists");
			}

			if (webuserRepository.findByEmail(webuser.getEmail()).isPresent()) {
				throw new WebUserWithEmailIdExistsException("Web user with email: " + webuser.getEmail() + "already exists");
			}

			if (webuserRepository.findByCustomerId(webuser.getCustomerId()).isPresent()) {
				throw new WebUserWithCustomerIdExistsException("Web user with customer id: " + webuser.getCustomerId() + "already exists");
			}


			return ResponseEntity.ok().body(webuserRepository.save(webuser));

		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@PutMapping("/{employeeId}")
	public ResponseEntity<Webuser> updateWebuser(@PathVariable(value = "employeeId") String employeeId,
			@RequestBody Webuser webuserDetails) throws ResourceNotFoundException {
		Webuser webuser = webuserRepository.findByEmployeeId(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Webuser not found for this id :: " + employeeId));

		webuser.setEmail(webuserDetails.getEmail());
		webuser.setLastName(webuserDetails.getLastName());
		webuser.setFirstName(webuserDetails.getFirstName());
		final Webuser updatedWebuser = webuserRepository.save(webuser);
		return ResponseEntity.ok(updatedWebuser);
	}

	@DeleteMapping("/{employeeId}")
	public Map<String, Boolean> deleteWebuser(@PathVariable(value = "employeeId") String employeeId)
			throws ResourceNotFoundException {
		Webuser webuser = webuserRepository.findByEmployeeId(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Webuser not found for this id :: " + employeeId));

		webuserRepository.delete(webuser);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
