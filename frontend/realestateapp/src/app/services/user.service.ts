import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { UserProfile } from '../models/UserProfile';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  userUrl = 'http://localhost:8080/api/v1/user';

  constructor(private http: HttpClient) {}

  getUserProfileById(userId: number): Observable<UserProfile> {
    return this.http.get<UserProfile>(this.userUrl + `/${userId}`);
  }

  getUserProperties(
    userId: number,
    pageNumber: number,
    pageSize: number
  ): Observable<any> {
    return this.http.get<any>(
      this.userUrl +
        `/${userId}/properties?pageNumber=${pageNumber}&pageSize=${pageSize}`
    );
  }

  updateUserProfile(user: any): Observable<UserProfile> {
    //zmienic na odpowiednie!!
    return this.http.patch<any>(this.userUrl, user);
  }

  getUserId(): Observable<number> | null {
    if (!localStorage.getItem('jwtToken')) return null;
    return this.http.get<number>(this.userUrl + '/selfId');
  }
}
