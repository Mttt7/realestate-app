import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginResponsePayload } from '../models/LoginResponsePayload';
import { LoginRequestPayload } from '../models/LoginRequestPayload';
import { HttpClient } from '@angular/common/http';
import { RegisterRequestPayload } from '../models/RegisterRequestPayload';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private authUrl = 'http://localhost:8080/api/v1/auth';

  constructor(private http: HttpClient, private router: Router) {}

  getLoggedInUserId(): string | null {
    return localStorage.getItem('userId');
  }

  login(credentials: LoginRequestPayload): Observable<LoginResponsePayload> {
    return this.http.post<LoginResponsePayload>(
      this.authUrl + '/login',
      credentials
    );
  }

  register(registerPayload: RegisterRequestPayload): Observable<any> {
    return this.http.post(this.authUrl + '/register', registerPayload);
  }

  checkUsernameAvailability(username: string): Observable<ExistsResponse> {
    return this.http.get<ExistsResponse>(
      this.authUrl + '/username/' + username
    );
  }

  setToken(token: LoginResponsePayload) {
    localStorage.setItem('jwtToken', token.accessToken);
  }

  logout() {
    localStorage.removeItem('jwtToken');
    localStorage.removeItem('userId');
    this.router.navigateByUrl('/login');
  }

  userIsLoggedIn(): boolean {
    return localStorage.getItem('jwtToken') !== null;
  }
}

interface ExistsResponse {
  available: boolean;
}
