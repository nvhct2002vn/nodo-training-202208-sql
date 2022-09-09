import {Component, OnInit} from '@angular/core';
import {IProduct} from "../entity/IProduct";

@Component({
  selector: 'app-produc-list',
  templateUrl: './produc-list.component.html',
  styleUrls: ['./produc-list.component.css']
})
export class ProducListComponent implements OnInit {
  products: IProduct[] = [
    {
      "productId": 1,
      "productName": "Leaf Rake",
      "productCode": "GDN-0011",
      "releaseDate": "March 19, 2016",
      "description": "Leaf rake with 48-inch wooden handle.",
      "price": 19.95,
      "starRating": 3.2,
      "imageUrl": "http://openclipart.org/image/300px/svg_to_png/26215/Anonymous_Leaf_Rake.png"
    },
    {
      "productId": 2,
      "productName": "Garden Cart",
      "productCode": "GDN-0023",
      "releaseDate": "March 18, 2016",
      "description": "15 gallon capacity rolling garden cart",
      "price": 32.99,
      "starRating": 4.2,
      "imageUrl": "http://openclipart.org/image/300px/svg_to_png/58471/garden_cart.png"
    },
    {
      "productId": 5,
      "productName": "Hammer",
      "productCode": "TBX-0048",
      "releaseDate": "May 21, 2016",
      "description": "Curved claw steel hammer",
      "price": 8.9,
      "starRating": 4.8,
      "imageUrl": "http://openclipart.org/image/300px/svg_to_png/73/rejon_Hammer.png"
    },
    {
      "productId": 8,
      "productName": "Saw",
      "productCode": "TBX-0022",
      "releaseDate": "May 15, 2016",
      "description": "15-inch steel blade hand saw",
      "price": 11.55,
      "starRating": 3.7,
      "imageUrl": "http://openclipart.org/image/300px/svg_to_png/27070/egore911_saw.png"
    },
    {
      "productId": 10,
      "productName": "Video Game Controller",
      "productCode": "GMG-0042",
      "releaseDate": "October 15, 2015",
      "description": "Standard two-button video game controller",
      "price": 35.95,
      "starRating": 4.6,
      "imageUrl": "http://openclipart.org/image/300px/svg_to_png/120337/xbox-controller_01.png"
    }
  ];


  listProduct: IProduct[] = [];

  constructor() {
  }

  //tạo ra 1 cái list rỗng để gán dữ liệu của products vào
  ngOnInit(): void {
    this.listProduct = this.products;
  }

  filterValue: string = '';//ánh xạ tới textbox search với ngModel

  isHidden = true;

  //1 cách khác là thêm trường check vào đối tượng đó, rồi click lấy ra index và tìm đói dượng đó theo index và change trạng thái của đối tượng đó.
  checkHidden() {
    this.isHidden = !this.isHidden;
  }

  filter() {
    //chọn sản phẩm có tên chứa tên giá trị nhập vào
    this.products = this.listProduct.filter(p => p.productName.toLowerCase().includes(this.filterValue))
  }

  numberStar: number | undefined;

  // tạo 1 method để nhận dữ liệu và gán cho biến numberStar
  nhanOpRating(sk: number) {
    this.numberStar = sk;
  }

}
