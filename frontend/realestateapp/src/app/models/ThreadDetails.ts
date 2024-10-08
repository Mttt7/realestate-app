import { UserProfile } from './UserProfile';

export interface ThreadDetails {
  id: number;
  lastMessage: string;
  lastMessageDate: Date;
  userOne: UserProfile;
  userTwo: UserProfile;
}
