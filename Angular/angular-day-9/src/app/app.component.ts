import {Component, ViewChild} from '@angular/core';
import {ToogleComponent} from "./toogle/toogle.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  @ViewChild('btnComponent') toggleComp?: ToogleComponent;

  title = true;

  ngAfterViewInit() {
    console.log(this.toggleComp);
  }
}
