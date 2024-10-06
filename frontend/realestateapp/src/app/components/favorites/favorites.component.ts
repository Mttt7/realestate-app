import { Component } from '@angular/core';
import { PropertyService } from '../../services/property.service';
import { Property } from '../../models/Property';
import { map, tap } from 'rxjs';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrl: './favorites.component.css',
})
export class FavoritesComponent {
  favorites: Property[] = [];

  constructor(private propertyService: PropertyService) {}

  ngOnInit() {
    this.getFavorites();
  }

  private getFavorites() {
    this.propertyService.getFavorites().subscribe({
      next: (data) => {
        this.favorites = (data as any).content as Property[];
      },
      error: (err) => {
        console.error('Subscription error:', err);
      },
    });
  }
}
