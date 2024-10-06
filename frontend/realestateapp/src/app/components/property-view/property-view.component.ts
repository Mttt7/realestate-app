import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Property } from '../../models/Property';
import { PropertyService } from '../../services/property.service';

@Component({
  selector: 'app-property-view',
  templateUrl: './property-view.component.html',
  styleUrl: './property-view.component.css',
})
export class PropertyViewComponent {
  propertyId: number = -1;
  property: Property | undefined = undefined;

  constructor(
    private route: ActivatedRoute,
    private propertyService: PropertyService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.propertyId = +params['id'];
      this.getProperty();
    });
  }

  private getProperty() {
    this.propertyService.getPropertyById(this.propertyId).subscribe(
      (res: any) => {
        this.property = res;
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
