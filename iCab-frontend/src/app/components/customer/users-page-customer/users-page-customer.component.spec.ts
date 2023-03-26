import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersPageCustomerComponent } from './users-page-customer.component';

describe('UsersPageCustomerComponent', () => {
  let component: UsersPageCustomerComponent;
  let fixture: ComponentFixture<UsersPageCustomerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsersPageCustomerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsersPageCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
