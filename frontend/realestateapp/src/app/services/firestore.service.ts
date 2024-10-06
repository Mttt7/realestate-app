import { Injectable } from '@angular/core';
import { AngularFireStorage } from '@angular/fire/compat/storage';
import {
  catchError,
  finalize,
  firstValueFrom,
  map,
  Observable,
  of,
  switchMap,
} from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class FirestoreService {
  constructor(
    private storage: AngularFireStorage,
    private authService: AuthService
  ) {}

  uploadFile(file: File) {
    const userId = this.authService.getLoggedInUserId();

    return this.storage.upload(`images/${userId}/${file.name}`, file);
  }

  getDownloadURL(filePath: string): Observable<string> {
    const fileRef = this.storage.ref(filePath);
    return fileRef.getDownloadURL();
  }
}
