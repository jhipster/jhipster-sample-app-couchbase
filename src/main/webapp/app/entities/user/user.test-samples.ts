import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: 'aa136c48-4e1b-4f71-9e9e-ba887ca37d92',
  login: '~vc@cOc\\^Jh9\\wo\\u-cYcqR',
};

export const sampleWithPartialData: IUser = {
  id: '57abb8a5-9308-4005-b1e8-6c4cad3a7d54',
  login: 'EnCoo',
};

export const sampleWithFullData: IUser = {
  id: '6e352873-da0b-4fe3-bed7-82eab4a4102b',
  login: 'lv',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
