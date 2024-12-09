package com.alten.back.authentication;

import lombok.*;


public record UserDTO (String username,
					String firstname,
					String email,
					String password){
}
