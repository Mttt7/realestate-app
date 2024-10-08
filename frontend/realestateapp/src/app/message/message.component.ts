import { Component } from '@angular/core';
import { MessageService } from '../services/message.service';
import { ThreadDetails } from '../models/ThreadDetails';
import { formatDistanceToNow } from 'date-fns';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrl: './message.component.css',
})
export class MessageComponent {
  threads: ThreadDetails[] = [];
  loading: boolean = false;

  constructor(private messageService: MessageService) {}

  ngOnInit(): void {
    this.loading = true;
    this.messageService.getAllThreads(0, 10).subscribe((threads) => {
      this.threads = threads.content;
      console.log(this.threads);
      this.loading = false;
    });
  }

  getTimeAgo(date: Date): string {
    return formatDistanceToNow(new Date(date), { addSuffix: true });
  }
}
