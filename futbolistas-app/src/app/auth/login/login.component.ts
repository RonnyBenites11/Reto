import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/guards/auth.service';
import { LoginService } from 'src/app/service/auth/login.service';
import { LoginRequest } from 'src/app/service/auth/loginRequest';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginError:string="";
  loginForm=this.formBuilder.group({
    username:['',[Validators.required,Validators.email]],
    password: ['',Validators.required],
  })
  constructor(
    private formBuilder:FormBuilder,
    private router:Router,
    private loginService: LoginService,
    private authService: AuthService) { }

  ngOnInit(): void {
  }

  get username(){
    return this.loginForm.controls.username;
  }

  get password()
  {
    return this.loginForm.controls.password;
  }

  login(){
    if(this.loginForm.valid){
      this.loginError="";
      this.loginService.login(this.loginForm.value as LoginRequest).subscribe({
        next: (response) => {
          this.authService.setToken(response.token); // Guardar el token en localStorage
          this.router.navigateByUrl('/inicio');
          this.loginForm.reset();
        },
        error: (errorData) => {
          console.error(errorData);
          this.loginError = 'Error al iniciar sesión. Verifique sus credenciales.';
        },
        complete: () => {
          console.info("Login completo");
        }
      });
    } else {
      this.loginForm.markAllAsTouched();
      alert("Error al ingresar los datos.");
    }
  }
}
