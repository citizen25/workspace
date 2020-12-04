import cv2
import os
import numpy as np
from PIL import Image

filename = 'bs512_lrg0.0003_lrd0.0001_ep300'

path = 'results_gray\\' + filename
imagePaths = [os.path.join(path,file_name) for file_name in os.listdir(path)]
for imagePath in imagePaths:
    img = Image.open(imagePath).convert('L')
    img_numpy = np.array(img, 'uint8')
    cv2.imwrite("results_gray\\" + filename + "\\" + imagePath.split("\\")[-1], img_numpy)
print("All Done")
