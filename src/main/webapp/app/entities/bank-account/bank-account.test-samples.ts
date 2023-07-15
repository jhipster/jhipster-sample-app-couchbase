import { IBankAccount, NewBankAccount } from './bank-account.model';

export const sampleWithRequiredData: IBankAccount = {
  id: '18e4d38a-cf45-4e49-bd7c-6a66f6e7a7c5',
  name: 'Azerbaijan',
  balance: 25837,
};

export const sampleWithPartialData: IBankAccount = {
  id: '042e4e7f-490c-4bb4-a3d1-94240c23fdbd',
  name: 'maroon Chips',
  balance: 78195,
};

export const sampleWithFullData: IBankAccount = {
  id: '255a9fa0-aa85-418e-b0c6-93dfaf9dcfb9',
  name: 'silver gray',
  balance: 93394,
};

export const sampleWithNewData: NewBankAccount = {
  name: 'Mercedes Oregon',
  balance: 81177,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
