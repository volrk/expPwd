package com.thibaut.demoPassWord.rest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thibaut.demoPassWord.pivot.Try;

@RestController
@RequestMapping("rest")
public class Rest {
	
	Map<String,String> mDp = new HashMap<>();
	Map<String, Try> tries = new HashMap<>();
	
	@PostConstruct
	public void test() {
		mDp.put("moi", "toi");
	}

	
    @GetMapping("/{user}/{password}")
    public boolean getBook(@PathVariable String user, @PathVariable String password) {
        return essaiMdp(user, password);
    }
    
    private boolean essaiMdp(String user, String password) {
    	if(tries.containsKey(user) && tries.get(user).getDate().plusSeconds((long)Math.pow(2l, tries.get(user).getConter())).isAfter(LocalDateTime.now())) {
    		return false;
    	}
    	if (mDp.containsKey(user) && mDp.get(user).equals(password)) {
    		tries.remove(user);
    		return true;
    	}
    	if(!tries.containsKey(user)) {
        	Try essai = new Try(1, LocalDateTime.now());
        	tries.put(user, essai);
    	} else {
    		Try essaiOld = tries.get(user);
    		Try essai = new Try(essaiOld.getConter() + 1, LocalDateTime.now());
        	tries.put(user, essai);
    	}

    	return false;
    }
    
    @GetMapping("/map")
    public Map<String, Try> getMap() {
        return tries;
    }
    
    @GetMapping("/{user}")
    public LocalDateTime testUser(@PathVariable String user) {
    	if(tries.containsKey(user)) {
    		return tries.get(user).getDate().plusSeconds((long)Math.pow(2l, tries.get(user).getConter()));
    	}
    	return LocalDateTime.now();
    }
    
}
