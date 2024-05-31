import { IBankAccount, NewBankAccount } from './bank-account.model';

export const sampleWithRequiredData: IBankAccount = {
  id: '18e4d38a-cf45-4e49-9bd7-c6a66f6e7a7c',
  name: 'switching',
  balance: 9633.99,
};

export const sampleWithPartialData: IBankAccount = {
  id: 'e7f490cb-b4e3-4d19-b424-0c23fdbd8263',
  name: 'prime lox cribbage',
  balance: 27388.79,
};

export const sampleWithFullData: IBankAccount = {
  id: 'faf9dcfb-9acf-4bbe-bada-22bc518454da',
  name: 'gosh operator',
  balance: 8448.22,
};

export const sampleWithNewData: NewBankAccount = {
  name: 'likewise shyly attentive',
  balance: 31275.42,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
