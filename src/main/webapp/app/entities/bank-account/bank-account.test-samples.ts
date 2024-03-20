import { IBankAccount, NewBankAccount } from './bank-account.model';

export const sampleWithRequiredData: IBankAccount = {
  id: '484c1b27-bc85-4e26-92a0-939d838e242e',
  name: 'really close where',
  balance: 2907.19,
};

export const sampleWithPartialData: IBankAccount = {
  id: 'bd062c05-b138-4b71-8516-ad3cf5db9569',
  name: 'behind',
  balance: 18520.01,
};

export const sampleWithFullData: IBankAccount = {
  id: '3435bc0f-c418-4d54-a3b1-fa3440f2c70f',
  name: 'truly',
  balance: 27019.02,
};

export const sampleWithNewData: NewBankAccount = {
  name: 'laryngitis',
  balance: 21374.45,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
