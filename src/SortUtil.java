import java.util.Random;

/**
 * designed by Steve Ke on 2017/2/16.
 *
 * @author Steve Ke
 *         e-mail  huangke7296@foxmail.com
 *         github  https://github.com/KoreHuang
 *         oschina https://git.oschina.net/steveKe
 * @version JDK 1.8.0_111
 * @since 2017/2/16
 */
public class SortUtil {
    /**
     *
     *  冒泡排序
     *
     * @param data
     * @return
     */
    public static int[] bubbleSort(int[] data){
        int len =data.length;
        boolean flag=true;
        for (int i=len-1;flag && i>=0;i--){
            flag=false;
            for (int j=0;j<i;j++){
                if(data[j]>data[j+1]){
                    data[j]=data[j+1]^data[j];
                    data[j+1]=data[j]^data[j+1];
                    data[j]=data[j+1]^data[j];
                    flag=true;
                }       //相邻元素之间不满足主序，则交换
            }
        }
        return data;
    }

    /**
     *
     *  简单选择排序
     *
     *  @param data
     * @return
     */
    public static int[] selSort(int[] data){
        int len=data.length;
        int k=0;
        for (int i=0;i<len-1;i++){
            k=i;
            for(int j=i+1;j<len;j++){
                if(data[j]<data[k]) k=j;
            }       //k为选出的最小元素的下标
            if(k!=i){
                data[i]^=data[k];
                data[k]^=data[i];
                data[i]^=data[k];
            }       //交换data[k] 和 data[i]
        }
        return data;
    }

    /**
     *
     *  插入排序
     *
     * @param data
     * @return
     */
    public static int[] insertSort(int[] data){
        int len=data.length;
        int  j=0,tmp=0;  //局部变量初始化

        for (int i=1;i<len;i++){
            tmp=data[i];
            for (j=i-1;j>-1 && data[j]>tmp;j--) data[j+1]=data[j];
            data[j+1]=tmp;
        }

        return data;
    }

    /**
     *  希尔排序
     * @param data
     */
    public static void shellSort(int []data){
        int gap;
        int i,j,k,m;
        for(gap=data.length/2;gap>0;gap/=2){
            for(i=0;i<gap;i++){
                for (m=i; m<data.length ;m+=gap) {
                    k=m;
                    for(j=m;j<data.length;j+=gap)
                        if(data[j]<data[k])k=j;
                    if(k!=m){
                        data[m]^=data[k];
                        data[k]^=data[m];
                        data[m]^=data[k];
                    }
                }
            }
        }
    }



    /**
     *
     *  归并排序
     *
     * @param data
     * @param left
     * @param right
     */
    public static void mergeSort(int [] data,int left,int right){
        if(left>=right) return ;
        mergeSort(data,left,(right+left)/2);
        mergeSort(data,(right+left)/2+1,right);
        merge(data,left,right);
    }

    public static void merge(int[] data,int left,int right){
        int mid=left+(right-left)/2;
        int i=left,j=mid+1,k=0;
        int []tmp=new int[right-left+1];
        while(i<mid+1||j<right+1){
            if(i>=mid+1||j<right+1&&data[i]>data[j]){
                tmp[k++]=data[j++];
            }else {
                tmp[k++]=data[i++];
            }
        }
        System.arraycopy(tmp,0,data,left,right-left+1);
    }

    /**
     *
     *  合并两个升序数组
     *
     * @param A
     * @param B
     * @return
     */
    public static int[] merge(int []A,int []B){
        int []C=new int[A.length+B.length];
        int i=0,j=0,k=0;
        while (i<A.length||j<B.length){
            if(i>=A.length||j<B.length&&A[i]>B[j]){
                C[k++]=B[j++];
            }else {
                C[k++]=A[i++];
            }
        }
        return  C;
    }

    public static void qsort(int []data,int left,int right){
        int lIndex=left;
        int rIndex=right;
        int pivot=data[(left+right)/2];
        int tmp=0;
        while (lIndex<rIndex){
            for(;data[lIndex]<pivot;lIndex++);
            for(;data[rIndex]>pivot;rIndex--);
            if(lIndex<=rIndex){
                tmp=data[lIndex];
                data[lIndex]=data[rIndex];
                data[rIndex]=tmp;
                lIndex++;
                rIndex--;
            }
        }
        if(lIndex<right){
            qsort(data,lIndex,right);
        }
        if(rIndex>left){
            qsort(data,left,rIndex);
        }

    }

    static class Item{
        String target;
        float expect;
    }

    /**
     * 为数字添加随机数据
     */
    public static void gloryPoint(Item []items){
        Random rand=new Random(System.currentTimeMillis());

        for(int i=0;i<items.length;i++){
            items[i].expect= rand.nextInt(5000000);
            if(items[i].expect>=2500000){
                items[i].target="找工作";
            }else {
                items[i].target="考研";
            }
        }
    }

    public static void main(String []args){
        Item[] destiny;
        destiny = new Item[5000000];
        for(int i=0;i<destiny.length;i++){
            destiny[i]=new Item();
        }
        Random dearGod=new Random(System.currentTimeMillis());
        int godPointer=dearGod.nextInt(5000000);
        gloryPoint(destiny);
        System.out.println(destiny[godPointer].target);
    }


    public static void heapSort(){

    }




//    public static void main(String args[]){
//        int data[]={3,1,2,5,7,4,9,6,8,0};
//        //data=bubbleSort(data);
//        //data=selSort(data);
//        //data=insertSort(data);
//        //mergeSort(data,0,9);
//        //shellSort(data);
//        qsort(data,0,data.length-1);
//        for(int item : data){
//            System.out.println(item);
//        }
///       int[] items={1,3,5,7,9,2,4,6,8,10};
//        merge(items,0,items.length-1);
//        for (int i:items){
//            System.out.print(i);
//        }
//        int a =1;
//        System.out.println(a^a);
//
//        System.out.println(a^a);
//
//        System.out.println();
//    }




}
