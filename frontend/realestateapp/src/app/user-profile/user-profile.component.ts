import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user.service';
import { UserProfile } from '../models/UserProfile';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css',
})
export class UserProfileComponent {
  userId: string | null = null;
  userProfile!: UserProfile;
  loading: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.loading = true;
    this.userId = this.route.snapshot.paramMap.get('id');
    this.userService
      .getUserProfileById(+this.userId!)
      .subscribe((userProfile) => {
        this.userProfile = userProfile;
        this.loading = false;
      });
  }
}