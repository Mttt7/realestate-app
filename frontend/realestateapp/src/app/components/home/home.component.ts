import { Component } from '@angular/core';
import { PropertyService } from '../../services/property.service';
import { Property } from '../../models/Property';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {
  properties: Property[] = [];

  constructor(private propertyService: PropertyService) {}

  ngOnInit() {
    this.getProperties();
  }

  getProperties() {
    this.propertyService.getProperties().subscribe((response) => {
      this.properties = (response as any).content as Property[];
    });
  }
}
