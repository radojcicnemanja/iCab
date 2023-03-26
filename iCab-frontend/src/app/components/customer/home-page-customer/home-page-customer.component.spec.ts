import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePageCustomerComponent } from './home-page-customer.component';

describe('HomePageCustomerComponent', () => {
  let component: HomePageCustomerComponent;
  let fixture: ComponentFixture<HomePageCustomerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomePageCustomerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomePageCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
