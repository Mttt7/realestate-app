//import { Role } from './Role';
export interface UserProfile {
  id: number;
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  about: string;
  phone: string;
  photoUrl: string;
  backgroundUrl: string;
  createdAt: Date;
  //roles: Role[];
}
