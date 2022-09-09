import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';

@Component({
  selector: 'progress-bar',
  template: `
    <div
      class="progress-bar-container"
      [style.backgroundColor]="backgroundColor"
    >
      <div
        class="progress"
        [style]="{
          backgroundColor: progressColor,
          width: progress + '%'
        }"
      ></div>
    </div>
  `,
  styles: [
    `
      .progress-bar-container,
      .progress {
        height: 20px;
      }

      .progress-bar-container {
        width: 100%;
      }
    `,
  ],
})
export class ProgressBarComponent implements OnChanges, OnInit {
  @Input() set progress(val: number) {
    this._progress = val;

    console.log(val);
  };

  private _progress = 50;

  get progress(): number {
    return this._progress;
  }

  @Input() backgroundColor = '#ccc';
  @Input() progressColor = 'tomato';
  hello = 'hello anh em nhe';

  constructor() {

  }

  ngOnChanges(changes: SimpleChanges) {
    console.log({
      progress: this.progress,
      backgroundColor: this.backgroundColor,
      progressColor: this.progressColor
    })
  }

  ngOnInit() {
  }
}
