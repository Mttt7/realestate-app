import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { LoginValidators } from '../../validators/login-validator';
import { AuthService } from '../../services/auth.service';
import { LoginResponsePayload } from '../../models/LoginResponsePayload';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { LoginRequestPayload } from '../../models/LoginRequestPayload';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent implements OnInit {
  credentialsFormGroup!: FormGroup;
  invalidCredentials: boolean = false;
  newAccount: boolean = false;
  loading = false;

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private router: Router,
    private userService: UserService
  ) {}

  credentials: LoginRequestPayload = {
    email: '',
    password: '',
  };

  ngOnInit(): void {
    this.route.queryParamMap.subscribe((params) => {
      if (params.get('registered') === 'true') {
        this.newAccount = true;
      } else {
        this.newAccount = false;
      }
    });
  }

  onPasswordChange($event: Event) {
    this.credentials.password = ($event.target as any).value;
  }
  onEmailChange($event: Event) {
    this.credentials.email = ($event.target as any).value;
  }

  login() {
    this.authService.login(this.credentials).subscribe(
      (data) => {
        this.authService.setToken(data);
        this.loading = false;
        this.userService.getUserId()?.subscribe((data: any) => {
          localStorage.removeItem('userId');
          localStorage.setItem('userId', data.id);
          this.router.navigateByUrl('/home');
        });
      },
      (error) => {
        this.loading = false;
        if (error.status == 401) {
          this.invalidCredentials = true;
        }
      }
    );
  }
}
