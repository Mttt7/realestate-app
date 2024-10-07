import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'realestateapp';
  id: string = '';

  constructor(private authService: AuthService) {}
  ngOnInit(): void {
    if (this.userIsLoggedIn()) {
      this.id = this.authService.getLoggedInUserId()!;
    } else {
      this.id = '';
    }
  }

  userIsLoggedIn(): boolean {
    return this.authService.userIsLoggedIn();
  }
}
