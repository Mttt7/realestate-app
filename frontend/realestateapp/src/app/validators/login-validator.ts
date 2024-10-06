import { FormControl, ValidationErrors } from '@angular/forms';

export class LoginValidators {
  static notOnlyWhitespace(control: FormControl): ValidationErrors | null {
    if (control.value != null && control.value.trim().length === 0) {
      return { notOnlyWhitespace: true };
    } else {
      return null;
    }
  }

  static validName(control: FormControl): ValidationErrors | null {
    const nameRegex = /^[A-Z][a-zA-Z]*$/;
    if (!nameRegex.test(control.value)) {
      return { validName: true };
    } else {
      return null;
    }
  }

  static validUsername(control: FormControl): ValidationErrors | null {
    const usernameRegex = /^[a-zA-Z0-9_-]*$/;
    if (!usernameRegex.test(control.value)) {
      return { validUsername: true };
    } else {
      return null;
    }
  }

  static validPassword(control: FormControl): ValidationErrors | null {
    const passwordRegex =
      /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
    if (!passwordRegex.test(control.value)) {
      return { validPassword: true };
    } else {
      return null;
    }
  }
}
