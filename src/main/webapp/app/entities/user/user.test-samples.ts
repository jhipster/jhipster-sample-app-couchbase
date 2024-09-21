import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: '3366b87f-3968-44a8-82ea-a684552bea4b',
  login: 'd`eE&@zZgDZ',
};

export const sampleWithPartialData: IUser = {
  id: 'd8adb465-be0d-49d6-ae90-c3849e1397a7',
  login: 'APtKm',
};

export const sampleWithFullData: IUser = {
  id: 'b78c2e7b-28ea-4298-b49e-479cbc599cc9',
  login: 'eR8',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
