import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Author} from "../authors";

@Component({
  selector: 'details-authors',
  template: `
    <div>
      <!--     khi dữ liệu được gán thì có thể hiển thị ra-->
      {{author?.name}}
      {{author?.email}}
      <!--      sự kiên click gọi đến @Output() select, select.emit đc chuyền vào 1 đối tượng author-->
      <button (click)="select.emit(author)">Selected</button>
      <button (click)="delete.emit(author?.id)">Delete</button>
      <br>
      <br>
    </div>
  `,
})
export class AuthorsDetailsComponent {

  @Input() author?: Author;
  @Output() select = new EventEmitter<Author>();
  @Output() delete = new EventEmitter<number>();

}
