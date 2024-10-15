import { UserProfile } from './UserProfile';

export interface SingleMessageResponsePayload {
  id: number;
  content: string;
  author: UserProfile;
  createdAt: Date;
}
