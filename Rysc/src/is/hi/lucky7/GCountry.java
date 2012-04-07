package is.hi.lucky7;

import java.nio.*;

import javax.microedition.khronos.opengles.GL10;

public class GCountry {
	private float vertices[] = {
			-0.9f, 1.0f, 0.0f,
			-1.0f, 0.0f, 0.0f,
			-0.9f, -0.9f, 0.0f,
			1.0f, -0.9f, 0.0f,
			1.0f, 1.0f, 0.0f,
	};	
	private short[] indices = { 0,1,2,0,2,3,0,3,4 };	
	private FloatBuffer vertexBuffer;	
	private ShortBuffer indexBuffer;
	
	public GCountry() {
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		
		vbb.order(ByteOrder.nativeOrder());
		vertexBuffer = vbb.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
		
		ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
		ibb.order(ByteOrder.nativeOrder());
		indexBuffer = ibb.asShortBuffer();
		indexBuffer.put(indices);
		indexBuffer.position(0);
	}
	
	public void draw(GL10 gl) {
		gl.glFrontFace(GL10.GL_CCW);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		
		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);
	}
}