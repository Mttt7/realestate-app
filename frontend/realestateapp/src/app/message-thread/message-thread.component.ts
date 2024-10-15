import { Component, ElementRef, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from '../services/message.service';
import { Message } from '../models/Message';
import { AuthService } from '../services/auth.service';
import { UserProfile } from '../models/UserProfile';

@Component({
  selector: 'app-message-thread',
  templateUrl: './message-thread.component.html',
  styleUrl: './message-thread.component.css',
})
export class MessageThreadComponent {
  loading: boolean = false;
  threadId: string = '';
  messages: Message[] = [];
  selfId: string = '';
  newMessage: string = '';
  receiverId: number = -1;
  userTwo: UserProfile = {} as UserProfile;
  pageNumber: number = 0;
  @ViewChild('messagesContainer') private messagesContainer!: ElementRef;

  constructor(
    private route: ActivatedRoute,
    private messageService: MessageService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.selfId = this.authService.getLoggedInUserId()!;
    this.loading = true;
    this.threadId = this.route.snapshot.paramMap.get('id')!;
    this.get20Messages(this.pageNumber);
  }
  isToday(sentAt: string | Date): boolean {
    const today = new Date();
    const sentDate = new Date(sentAt);

    return (
      sentDate.getDate() === today.getDate() &&
      sentDate.getMonth() === today.getMonth() &&
      sentDate.getFullYear() === today.getFullYear()
    );
  }

  get20Messages(pageNumber: number): void {
    this.messageService
      .getLast20Messages(+this.threadId, pageNumber)
      .subscribe((threadResponse) => {
        this.messages = [...this.messages, ...threadResponse.messages.content];
        if (threadResponse.userOne.id === +this.selfId) {
          this.receiverId = threadResponse.userTwo.id;
          this.userTwo = threadResponse.userTwo;
        } else {
          this.receiverId = threadResponse.userOne.id;
          this.userTwo = threadResponse.userOne;
        }
        this.loading = false;
        this.scrollToBottom();
      });
  }

  scrollToBottom(): void {
    try {
      this.messagesContainer.nativeElement.scrollTop =
        this.messagesContainer.nativeElement.scrollHeight;
    } catch (err) {
      console.error('Scroll error:', err);
    }
  }

  sendMessage(): void {
    if (this.newMessage.trim() === '') {
      return;
    }

    this.messageService.sendMessage(this.newMessage, this.receiverId).subscribe(
      (response) => {
        const newMessageResponse = {
          id: response.id,
          content: response.content,
          createdAt: response.createdAt,
          authorId: response.author.id,
        } as Message;
        this.messages.unshift(newMessageResponse);
        this.newMessage = '';
      },
      (error) => {
        console.error('Błąd podczas wysyłania wiadomości', error);
      }
    );
  }

  loadMore() {
    this.pageNumber++;
    this.get20Messages(this.pageNumber);
  }
}
