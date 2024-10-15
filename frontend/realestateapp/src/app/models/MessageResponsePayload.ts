import { Message } from './Message';
import { UserProfile } from './UserProfile';

export interface MessageResponsePayload {
  messages: {
    content: Message[];
    totalElements: number;
    totalPages: number;
    last: boolean;
    empty: boolean;
  };
  userOne: UserProfile;
  userTwo: UserProfile;
}
