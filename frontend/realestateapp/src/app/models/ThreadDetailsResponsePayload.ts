import { ThreadDetails } from './ThreadDetails';

export interface ThreadDetailsResponsePayload {
  content: ThreadDetails[];
  totalElements: number;
  totalPages: number;
  last: boolean;
  empty: boolean;
}
