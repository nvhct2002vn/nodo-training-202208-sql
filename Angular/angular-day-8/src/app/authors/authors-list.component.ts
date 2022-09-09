import {Component, Input, OnInit} from '@angular/core';
import {Author, authors} from "../authors";

@Component({
  selector: 'list-authors',
  template: `
    <!--  dùng for để duyệt mảng đố tượng đó và gán vào đói tượng [author]
      [author] gán input nên có thể gọi sang component khác để gán dữ liệu

      - (select)="onSelected($event), details-authors nó sẽ lắng nghe cái sự kiện select(@output) , lúc này nó chưa có cái để
      gán, nên tạo ra 1 method để gán đa ta. method đc chuyền vào 1 event (event này chính là data đc nhận vào cảu input)
      -->
    <details-authors *ngFor="let author of authors" [author]="author" (select)="onSelected($event)"
                     (delete)="onDelete($event)">
    </details-authors>
    <p>current selected author: {{currentAuthor.name}} - {{currentAuthor.email}}</p>
  `
})
export class AuthorsListComponent {

  //tạo ra 1 biến gắn nó bằng 1 mảng đối tượng
  authors = authors;
  //đối tượng hiển thị
  currentAuthor = authors[0];

  onSelected(selectedAuthor: Author) {
    // @ts-ignore
    this.currentAuthor = selectedAuthor;
  }

  onDelete(id: number) {
    // lọc ra các mã khác với mã mình chọn
    // @ts-ignore
    this.authors = this.authors.filter(
      author => {
        return author.id !== id;
      }
    );
    if (this.currentAuthor.id === id) {
      this.currentAuthor = this.authors[0];
    }
  }
}
