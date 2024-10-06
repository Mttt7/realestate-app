import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Property } from '../models/Property';

@Injectable({
  providedIn: 'root',
})
export class PropertyService {
  constructor(private http: HttpClient) {}

  getProperties() {
    return this.http.get(
      `http://localhost:8080/api/v1/property/all?pageNumber=${0}&pageSize=${10}`
    );
  }

  getFavorites() {
    return this.http.get(
      `http://localhost:8080/api/v1/property/favourites?pageNumber=${0}&pageSize=${10}`
    );
  }

  toggleFavorite(id: number) {
    return this.http.post(
      `http://localhost:8080/api/v1/property/favourite/${id}`,
      {}
    );
  }

  getPropertyById(id: number) {
    return this.http.get(`http://localhost:8080/api/v1/property/${id}`);
  }

  addProperty(property: any): Observable<Property> {
    return this.http.post<Property>(
      `http://localhost:8080/api/v1/property/add`,
      property
    );
  }
}
