import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import {
  HTTP_INTERCEPTORS,
  HttpClient,
  HttpClientModule,
  HttpHandler,
  provideHttpClient,
  withInterceptorsFromDi,
} from '@angular/common/http';
import { AuthInterceptorService } from './services/auth-interceptor.service';
import { HomeComponent } from './components/home/home.component';
import { ReactiveFormsModule } from '@angular/forms';
import { PropertySmallViewComponent } from './components/property-small-view/property-small-view.component';
import { FavoritesComponent } from './components/favorites/favorites.component';
import { PropertyViewComponent } from './components/property-view/property-view.component';
import { AddComponent } from './components/add/add.component';
import { LoaderComponent } from './loader/loader.component';
import { initializeApp, provideFirebaseApp } from '@angular/fire/app';
import { getFirestore, provideFirestore } from '@angular/fire/firestore';
import { AngularFireModule } from '@angular/fire/compat';
import { AngularFireAuthModule } from '@angular/fire/compat/auth';
import { AngularFirestoreModule } from '@angular/fire/compat/firestore';
import { environment } from '../environments/environment.development';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    PropertySmallViewComponent,
    FavoritesComponent,
    PropertyViewComponent,
    AddComponent,
    LoaderComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    AngularFireModule.initializeApp({
      apiKey: process.env['NG_FIREBASE_API_KEY'],
      authDomain: process.env['NG_FIREBASE_AUTH_DOMAIN'],
      projectId: process.env['NG_FIREBASE_PROJECT_ID'],
      storageBucket: process.env['NG_FIREBASE_STORAGE_BUCKET'],
      messagingSenderId: process.env['NG_FIREBASE_MESSAGING_SENDER_ID'],
      appId: process.env['NG_FIREBASE_APP_ID'],
      measurementId: process.env['NG_FIREBASE_MEASUREMENT_ID'],
    }),
    AngularFireAuthModule,
    AngularFirestoreModule,
  ],
  providers: [
    provideHttpClient(withInterceptorsFromDi()),
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
