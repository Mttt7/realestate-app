import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Property } from '../../models/Property';
import { PropertyService } from '../../services/property.service';
import { FirestoreService } from '../../services/firestore.service';
import { finalize, forkJoin, map, switchMap } from 'rxjs';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrl: './add.component.css',
})
export class AddComponent {
  photos: File[] = [];
  photosPreview: { url: string; name: string }[] = [];
  draggedIndex: number | null = null;

  propertyForm!: FormGroup;
  loading!: boolean;

  constructor(
    private fb: FormBuilder,
    private propertyService: PropertyService,
    private firestoreService: FirestoreService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loading = false;
    this.propertyForm = this.fb.group({
      name: ['', Validators.required],
      price: [null, [Validators.required, Validators.min(0)]],
      description: ['', Validators.required],
      country: ['', Validators.required],
      city: ['', Validators.required],
      buildingNumber: [''],
      street: [''],
      address: [''],
      estate: ['', Validators.required],
      floor: [null, [Validators.min(0)]],
      floors: [null, [Validators.required, Validators.min(1)]],
      floorCount: [null, [Validators.required, Validators.min(1)]],
      windows: ['', Validators.required],
      rooms: [null, [Validators.required, Validators.min(1)]],
      bathrooms: [null, [Validators.required, Validators.min(1)]],
      sizeMeters: [null, [Validators.required, Validators.min(0)]],
      parkingSpaces: [null, [Validators.required, Validators.min(0)]],
      resaleMarket: [false],
      balconies: [0, [Validators.required, Validators.min(0)]],
      yearOfConstruction: [null, [Validators.required, Validators.min(1800)]], // Przykład: nie starsze niż 1800 rok
      availableFrom: [null, Validators.required],
      heating: ['', Validators.required],
      elevator: [false],
      levelOfFinish: ['', Validators.required],
      rent: [null, [Validators.required, Validators.min(0)]],
    });
  }

  async onSubmit() {
    if (this.propertyForm.valid) {
      const newProperty: Property = {
        ...this.propertyForm.value,
        images: [],
      };
      this.loading = true;

      // Tablica do przechowywania obietnic
      const uploadPromises = this.photos.map(
        (file, index) =>
          new Promise<{ url: string; order: number } | null>(
            (resolve, reject) => {
              this.firestoreService
                .uploadFile(file)
                .snapshotChanges()
                .pipe(
                  finalize(() => {
                    // Uzyskaj URL po zakończeniu przesyłania
                    this.firestoreService
                      .getDownloadURL(
                        `images/${this.authService.getLoggedInUserId()}/${
                          file.name
                        }`
                      )
                      .subscribe({
                        next: (downloadURL) => {
                          if (downloadURL) {
                            resolve({ url: downloadURL, order: index });
                          } else {
                            console.error(
                              `Failed to get download URL for ${file.name}`
                            );
                            resolve(null); // Zwróć null, jeśli nie uda się uzyskać URL
                          }
                        },
                        error: (error) => {
                          console.error(
                            `Error fetching download URL for ${file.name}: ${error}`
                          );
                          reject(error); // Zgłoś błąd
                        },
                      });
                  })
                )
                .subscribe(); // Uruchom subskrypcję
            }
          )
      );

      try {
        // Czekaj na zakończenie wszystkich przesyłek
        const images = await Promise.all(uploadPromises);

        // Filtruj tylko te, które są nie-null
        const validImages = images.filter(
          (img): img is { url: string; order: number } => img !== null
        );

        newProperty.images.push(...validImages); // Dodaj wszystkie obrazki do nowej nieruchomości
      
        this.propertyService
          .addProperty(newProperty)
          .subscribe((property: Property) => {
            this.router.navigate([`/properties/${property.id}`]);
            this.loading = false;
          });
      } catch (error) {
        console.error('Error uploading photos:', error);
      }
    }
  }

  onFileSelected(event: any) {
    const selectedFiles = Array.from(event.target.files) as File[];
    this.addPhotos(selectedFiles);
  }

  onDrop(event: DragEvent) {
    event.preventDefault();
    const droppedFiles = Array.from(event.dataTransfer?.files || []) as File[];

    this.addPhotos(droppedFiles);
  }

  onDragOver(event: DragEvent) {
    event.preventDefault();
  }

  onDragLeave(event: DragEvent) {
    event.preventDefault();
  }

  onDragStart(event: DragEvent, index: number) {
    this.draggedIndex = index;
    event.dataTransfer?.setData('text/plain', index.toString());
  }

  onDragOverPhoto(event: DragEvent) {
    event.preventDefault();
    event.dataTransfer!.dropEffect = 'move';
  }

  onDropPhoto(event: DragEvent, targetIndex: number) {
    event.preventDefault();
    if (this.draggedIndex !== null) {
      const draggedPhoto = this.photosPreview[this.draggedIndex];
      const draggedFile = this.photos[this.draggedIndex];

      this.photosPreview.splice(this.draggedIndex, 1);
      this.photosPreview.splice(targetIndex, 0, draggedPhoto);

      this.photos.splice(this.draggedIndex, 1);
      this.photos.splice(targetIndex, 0, draggedFile);

      this.draggedIndex = null;
    }
  }

  onDragLeavePhoto(event: DragEvent) {
    event.preventDefault();
  }

  addPhotos(files: File[]) {
    if (this.photos.length + files.length > 10) {
      alert('You can upload a maximum of 10 photos.');
      return;
    }

    for (let file of files) {
      if (this.photos.length < 10) {
        this.photos.push(file);
        this.previewPhoto(file);
      }
    }

    if (this.photos.length < 5) {
    }
  }

  previewPhoto(file: File) {
    const reader = new FileReader();
    reader.onload = (e: any) => {
      this.photosPreview.push({ url: e.target.result, name: file.name });
    };
    reader.readAsDataURL(file);
  }

  removePhoto(index: number) {
    this.photos.splice(index, 1);
    this.photosPreview.splice(index, 1);
  }

  getShortenedFileName(fileName: string): string {
    const extension = fileName.slice(fileName.lastIndexOf('.'));
    const baseName = fileName.slice(0, fileName.lastIndexOf('.'));

    if (baseName.length > 10) {
      return `${baseName.slice(0, 10)}...${extension}`;
    } else {
      return fileName;
    }
  }
}
