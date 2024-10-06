import { UserProfile } from './UserProfile';

export interface Property {
  id: number;
  author: UserProfile;
  name: string;
  price: number;
  description: string;
  country: string;
  city: string;
  street?: string;
  address?: string;
  estate: string;
  floor: number;
  floors: number;
  rooms: number;
  bathrooms: number;
  sizeMeters: number;
  parkingSpaces: number;
  resaleMarket: boolean;
  balconies: number;
  yearOfConstruction: number;
  availableFrom: number;
  heating: string;
  elevator: boolean;
  levelOfFinish: string;
  rent: number;

  images: {
    url: string;
    order: number;
  }[];

  favorite?: boolean;
}
