import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from "@angular/core";

@Component({
  selector: 'app-star',
  templateUrl: './star.component.html',
  styleUrls: ['./star.component.css']
})
export class StarComponent implements OnChanges {
  starWidth: number;
  @Input() rating: number;
  // tạo 1 input = 1 sự kiện
  @Output() opRatingCheck = new EventEmitter();

  constructor() {
    this.rating = 0
    this.starWidth = this.rating * 86 / 5;

  }

  ngOnChanges(): void {
    this.starWidth = this.rating * 86 / 5;
  }

  //khi click sẽ gán cho output số sao lấy được từ input,
  // nó sẽ phát ra 1 sự kiện chưa thằng sao đc gán vào đó và truyền ra bên ngoài
  onClick() {
    this.opRatingCheck.emit(this.rating);
  }
}
