package bb.j2ray.tools;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class BinaryData {
    private static final int DATA_BLOCK_SIZE=655360;
    private final List<byte[]> dataList=new ArrayList<>();
    private long length;

    public BinaryData() {
    }

    public BinaryData(long length) {
        this.length=length;
        if(length<=DATA_BLOCK_SIZE){
            dataList.add(new byte[(int) length]);
        }else{
            int cc= (int) (length/DATA_BLOCK_SIZE);
            int yu= (int) (length%DATA_BLOCK_SIZE);
            for(int i=0;i<cc;i++){
                dataList.add(new byte[cc]);
            }

            if(yu>0){
                dataList.add(new byte[yu]);
            }
        }
    }

    public byte[] toBytes(){
        int cov=0;
        for(byte[] bb:dataList){
            cov+=bb.length;
        }

        byte[] bbm=new byte[cov];
        int iim=0;
        for(byte[] bb:dataList){
            System.arraycopy(bb,0,bbm,iim,bb.length);
            iim+=bb.length;
        }
        return bbm;

    }



    public void read(InputStream is) throws Exception {
        for(byte[] bb:dataList){
            int d=is.read(bb);
            if(d<bb.length){
                throw new Exception("read data length="+d+",but expect="+bb.length);
            }
        }
    }

    public void write(OutputStream os) throws Exception {
        for(byte[] bb:dataList){
            os.write(bb);
        }
    }
    public long getLength() {
        return length;
    }

    public List<byte[]> getDataList() {
        return dataList;
    }

    public void setLength(long length) {
        this.length = length;
    }
}
