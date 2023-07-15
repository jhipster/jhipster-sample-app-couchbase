import { IBankAccount, NewBankAccount } from './bank-account.model';

export const sampleWithRequiredData: IBankAccount = {
  id: '18e4d38a-cf45-4e49-9bd7-c6a66f6e7a7c',
  name: 'Azerbaijan',
  balance: 8466,
};

export const sampleWithPartialData: IBankAccount = {
  id: '042e4e7f-490c-4bb4-be3d-194240c23fdb',
  name: 'Tools Tasty',
  balance: 25623,
};

export const sampleWithFullData: IBankAccount = {
  id: '255a9fa0-aa85-418e-a30c-693dfaf9dcfb',
  name: 'silver gray',
  balance: 30603,
};

export const sampleWithNewData: NewBankAccount = {
  name: 'Mercedes Schroeder',
  balance: 18410,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
