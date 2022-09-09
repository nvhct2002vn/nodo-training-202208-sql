import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'angular-day-6';

  isDanger =false;
  isWarning = false;
  classCss = "red-border yellow-background";
}
