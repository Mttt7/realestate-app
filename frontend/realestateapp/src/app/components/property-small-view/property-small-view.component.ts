import { Component, Input } from '@angular/core';
import { Property } from '../../models/Property';
import { PropertyService } from '../../services/property.service';

@Component({
  selector: 'app-property-small-view',
  templateUrl: './property-small-view.component.html',
  styleUrl: './property-small-view.component.css',
})
export class PropertySmallViewComponent {
  constructor(private propertyService: PropertyService) {}

  onHeartClick() {
    this.propertyService.toggleFavorite(this.property.id).subscribe((res) => {
      this.property.favorite = !this.property.favorite;
    });
  }

  @Input() property!: Property;

  rotated = false;
}
