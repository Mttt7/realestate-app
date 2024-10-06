import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { LoginValidators } from '../../validators/login-validator';
import { RegisterRequestPayload } from '../../models/RegisterRequestPayload';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
})
export class RegisterComponent implements OnInit {
  credentialsFormGroup!: FormGroup;
  invalidCredentials: boolean = false;
  loading = false;
  passwordDontMath = false;
  usernameTaken = false;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.credentialsFormGroup = this.formBuilder.group({
      credentials: this.formBuilder.group({
        username: new FormControl('', [
          Validators.required,
          Validators.minLength(4),
          Validators.maxLength(20),
          LoginValidators.notOnlyWhitespace,
          LoginValidators.validUsername,
        ]),
        password: new FormControl('', [
          Validators.required,
          Validators.minLength(4),
          Validators.maxLength(20),
          LoginValidators.notOnlyWhitespace,
          LoginValidators.validPassword,
        ]),
        passwordRepeated: new FormControl('', [
          Validators.required,
          Validators.minLength(4),
          Validators.maxLength(20),
          LoginValidators.notOnlyWhitespace,
        ]),
        firstName: new FormControl('', [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(25),
          LoginValidators.notOnlyWhitespace,
          LoginValidators.validName,
        ]),
        lastName: new FormControl('', [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(25),
          LoginValidators.notOnlyWhitespace,
          LoginValidators.validName,
        ]),
      }),
    });
  }
  get username() {
    return this.credentialsFormGroup.get('credentials.username');
  }
  get password() {
    return this.credentialsFormGroup.get('credentials.password');
  }
  get passwordRepeated() {
    return this.credentialsFormGroup.get('credentials.passwordRepeated');
  }
  get firstName() {
    return this.credentialsFormGroup.get('credentials.firstName');
  }
  get lastName() {
    return this.credentialsFormGroup.get('credentials.lastName');
  }

  register() {
    if (this.usernameTaken) return;
    if (
      this.username?.valid &&
      this.password?.valid &&
      this.passwordRepeated?.valid &&
      this.firstName?.valid &&
      this.lastName?.valid
    ) {
      if (this.password?.value != this.passwordRepeated?.value) {
        this.passwordDontMath = true;
        return;
      } else this.passwordDontMath = false;

      let registerPayload = {
        username: this.username?.value,
        password: this.password?.value,
        passwordRepeated: this.passwordRepeated?.value,
        firstName: this.firstName?.value,
        lastName: this.lastName?.value,
      } as RegisterRequestPayload;
      this.authService.register(registerPayload).subscribe((data) => {
        if (data.message == 'success') {
          this.router.navigateByUrl('/login?registered=true');
        } else {
          console.log(data);
        }
      });
    } else {
      this.username?.markAsDirty();
      this.password?.markAsDirty();
      this.passwordRepeated?.markAsDirty();
      this.firstName?.markAsDirty();
      this.lastName?.markAsDirty();
    }
  }

  checkUsername() {
    if (this.username?.value == '') return;
    this.authService
      .checkUsernameAvailability(this.username?.value)
      .subscribe((data) => {
        this.usernameTaken = !data.available;
      });
  }
}
