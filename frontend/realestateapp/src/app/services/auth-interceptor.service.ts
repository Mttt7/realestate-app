import { Injectable } from '@angular/core';
import { Observable, from, lastValueFrom } from 'rxjs';
import { HttpEvent, HttpHandler, HttpRequest } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuthInterceptorService {
  constructor() {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return from(this.handleAccess(request, next));
  }

  private async handleAccess(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Promise<HttpEvent<any>> {
    //const securedEndpoints = ['http://localhost:5000/api/v1/posts', 'http://localhost:5000/api/v1/user'];
    const securedEndpoints = ['http://localhost:8080/api/v1'];

    if (
      securedEndpoints.some((url) => request.urlWithParams.includes(url)) &&
      !securedEndpoints.some((url) =>
        request.urlWithParams.includes('http://localhost:8080/api/v1/auth')
      )
    ) {
      const accessToken = localStorage.getItem('jwtToken');

      request = request.clone({
        setHeaders: {
          Authorization: 'Bearer ' + accessToken,
        },
      });
    }
    return await lastValueFrom(next.handle(request));
  }
}
