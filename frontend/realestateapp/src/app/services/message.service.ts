import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ThreadDetailsResponsePayload } from '../models/ThreadDetailsResponsePayload';
import { Observable } from 'rxjs';
import { ThreadDetails } from '../models/ThreadDetails';

@Injectable({
  providedIn: 'root',
})
export class MessageService {
  messageUrl = 'http://localhost:8080/api/v1/message';
  threadUrl = 'http://localhost:8080/api/v1/thread';

  constructor(private http: HttpClient) {}

  getAllThreads(
    pageNumber: number,
    pageSize: number
  ): Observable<ThreadDetailsResponsePayload> {
    return this.http.get<ThreadDetailsResponsePayload>(
      this.threadUrl + '/all?pageNumber=' + pageNumber + '&pageSize=' + pageSize
    );
  }
}
