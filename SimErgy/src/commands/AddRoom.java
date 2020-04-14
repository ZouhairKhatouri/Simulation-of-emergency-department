package commands;

import clui.SimErgyCLUI;
import core.ED;
import resource.*;
import throwables.ArgumentsMismatchException;
import throwables.ExistantEntityError;
import throwables.NoneExistantEntityError;
import throwables.SyntaxErrorException;

public class AddRoom extends Command{
	
	private String edName;
	private String roomType;
	private String roomName;
	private Room room;

	public AddRoom(SimErgyCLUI clui,String[] args) throws SyntaxErrorException, ArgumentsMismatchException {
		
		super(clui,args);
		if(args.length==3) {
			
			this.edName = args[0];
			this.roomType = args[1];
			this.roomName = args[2];

			if(roomType.equals("BloodTestLab")) {
				this.room = new BloodTestLab(roomName);
			}
			else if(roomType.equals("MRIroom")) {
				this.room = new MRIroom(roomName);
			}
			else if(roomType.equals("RadiographyRoom")) {
				this.room = new RadiographyRoom(roomName);
			}
			else if(roomType.equals("ShockRoom")) {
				this.room = new ShockRoom(roomName);
			}
			else if(roomType.equals("BoxRoom")) {
				this.room = new BoxRoom(roomName);
			}
			else if(roomType.equals("WaitingRoom")) {
				this.room = new WaitingRoom(roomName);
			}
			else {
				throw new SyntaxErrorException(args);
			}
		}
		else {
			throw new ArgumentsMismatchException(args);
		}
	}

	@Override
	public void execute() {
		if(getClui().edMap.containsKey(edName)) {
			ED edepartment = getClui().edMap.get(edName);
			
			int ID = edepartment.nextID();
			
			if(roomType.equals("BloodTestLab")) {
				for(Room r:  edepartment.bloodTestLabs) {
					if(r.name.equals(roomName)) {
						throw new ExistantEntityError(getArgs());
					}
				}
				room.setID(edepartment.nextID());
				edepartment.bloodTestLabs.add((BloodTestLab) room);
			}
			else if(roomType.equals("MRIroom")) {
				for(Room r:  edepartment.mriRooms) {
					if(r.name.equals(roomName)) {
						throw new ExistantEntityError(getArgs());
					}
				}
				room.setID(ID);
				edepartment.mriRooms.add((MRIroom) room);
			}
			else if(roomType.equals("RadiographyRoom")) {
				for(Room r:  edepartment.radiographyRooms) {
					if(r.name.equals(roomName)) {
						throw new ExistantEntityError(getArgs());
					}
				}
				room.setID(ID);
				edepartment.radiographyRooms.add((RadiographyRoom) room);
			}
			else if(roomType.equals("ShockRoom")) {
				for(Room r:  edepartment.shockRooms) {
					if(r.name.equals(roomName)) {
						throw new ExistantEntityError(getArgs());
					}
				}
				room.setID(ID);
				edepartment.shockRooms.add((ShockRoom) room);
			}
			else if(roomType.equals("BoxRoom")) {
				for(Room r:  edepartment.boxRooms) {
					if(r.name.equals(roomName)) {
						throw new ExistantEntityError(getArgs());
					}
				}
				room.setID(ID);
				edepartment.boxRooms.add((BoxRoom) room);
			}
			else if(roomType.equals("WaitingRoom")) {
				for(Room r:  edepartment.waitingRooms) {
					if(r.name.equals(roomName)) {
						throw new ExistantEntityError(getArgs());
					}
				}
				room.setID(ID);
				edepartment.waitingRooms.add((WaitingRoom) room);
			}
			
			System.out.println("This room was successfully added, he got the ID number: "+ID);
			return;
		}
		else {
			throw new NoneExistantEntityError(this.getArgs());
		}
	}

}
