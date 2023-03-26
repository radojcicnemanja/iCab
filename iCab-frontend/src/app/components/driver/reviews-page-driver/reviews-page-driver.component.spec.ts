import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewsPageDriverComponent } from './reviews-page-driver.component';

describe('ReviewsPageDriverComponent', () => {
  let component: ReviewsPageDriverComponent;
  let fixture: ComponentFixture<ReviewsPageDriverComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReviewsPageDriverComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReviewsPageDriverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
