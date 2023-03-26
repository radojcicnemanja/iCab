import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePageDriverComponent } from './home-page-driver.component';

describe('HomePageDriverComponent', () => {
  let component: HomePageDriverComponent;
  let fixture: ComponentFixture<HomePageDriverComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomePageDriverComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomePageDriverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
